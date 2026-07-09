import { useEffect, useState } from "react";

import SkillService from "../services/SkillService";

import "../css/skills.css";
import { BsBluesky } from "react-icons/bs";

export default function Skills() {

    const userId = sessionStorage.getItem("userId");

    const [skills, setSkills] = useState([]);

    const [skillName, setSkillName] = useState("");

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadSkills();

    }, []);

    async function loadSkills() {

        try {

            const response =
                await SkillService.getSkillsByUser(userId);

            setSkills(response.data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    }

    async function addSkill() {

        if (skillName.trim() === "") {

            alert("Enter Skill");

            return;

        }

        try {
            await SkillService.addSkill({
                skillName: skillName,
                userId: parseInt(userId)
            });
            alert("Skill Added Successfully");
            setSkillName("");
            loadSkills();
        }
        catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Failed to Add Skill";
            alert("Failed to Add Skill: " + msg);
        }

    }

    async function deleteSkill(skillId) {

        if (!window.confirm("Delete Skill?")) {

            return;

        }

        try {
            await SkillService.deleteSkill(skillId);
            alert("Skill Deleted Successfully");
            loadSkills();
        }
        catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Failed to Delete Skill";
            alert("Failed to Delete Skill: " + msg);
        }

    }

    if (loading) {

        return (

            <div className="loading">

                <h2>Loading Skills...</h2>

            </div>

        );

    }

    return (

        <div
    className="skills-page"
    style={{
        background:"lightblue",
        minHeight: "100vh",
        padding: "30px"
    }}
>

            <h1>

                My Skills

            </h1>

            <div className="skill-input-box">

                <input

                    type="text"

                    placeholder="Enter Skill"

                    value={skillName}

                    onChange={(e) => setSkillName(e.target.value)}

                />

                <button

                    onClick={addSkill}

                >

                    Add Skill

                </button>

            </div>

            {

                skills.length === 0 ?

                    <div className="empty-box">

                        No Skills Added

                    </div>

                    :

                    <table
    className="skills-table"
    style={{
        background: "#daa3e7",
        borderRadius: "18px",
        overflow: "hidden",
        boxShadow: "0 10px 25px rgba(0,0,0,0.15)"
    }}
>

                        <thead>

                            <tr>

                                <th>Skill ID</th>

                                <th>Skill Name</th>

                                <th>Action</th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                skills.map(skill => (

                                    <tr key={skill.skillId}>

                                        <td>

                                            {skill.skillId}

                                        </td>

                                        <td>

                                            {skill.skillName}

                                        </td>

                                        <td>

                                            <button

                                                className="delete-btn"

                                                onClick={() => deleteSkill(skill.skillId)}

                                            >

                                                Delete

                                            </button>

                                        </td>

                                    </tr>

                                ))

                            }

                        </tbody>

                    </table>

            }

        </div>

    );

}
