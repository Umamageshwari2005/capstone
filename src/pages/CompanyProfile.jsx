import { useEffect, useState } from "react";

import UserService from "../services/UserService";

import "../css/companyProfile.css";

export default function CompanyProfile() {

    const employerId = sessionStorage.getItem("userId");

    const [company, setCompany] = useState({

        fullName: "",

        email: "",

        phone: "",

        address: "",

        website: "",

        description: ""

    });

    useEffect(() => {

        loadCompany();

    }, []);

    async function loadCompany() {

        try {

            const response =

                await UserService.getUser(employerId);

            setCompany({

                fullName: response.data.fullName || "",

                email: response.data.email || "",

                phone: response.data.phone || "",

                address: response.data.address || "",

                website: response.data.website || "",

                description: response.data.description || ""

            });

        }

        catch (error) {

            console.log(error);

        }

    }

    function handleChange(e) {

        setCompany({

            ...company,

            [e.target.name]: e.target.value

        });

    }

    async function updateProfile(e) {

        e.preventDefault();

        try {
            const currentRes = await UserService.getUser(employerId);
            const updatedData = {
                ...currentRes.data,
                fullName: company.fullName,
                email: company.email,
                phone: company.phone,
                address: company.address,
                website: company.website,
                description: company.description
            };

            await UserService.updateUser(employerId, updatedData);
            alert("Company profile updated successfully.");
            loadCompany();
        }
        catch (error) {
            console.log(error);
            alert("Failed to Update Company Profile");
        }

    }

    async function deleteProfile() {

        if (!window.confirm("Are you sure you want to clear/delete your company profile details?")) {
            return;
        }

        try {
            const currentRes = await UserService.getUser(employerId);
            const clearedData = {
                ...currentRes.data,
                phone: "",
                address: "",
                website: "",
                description: ""
            };

            await UserService.updateUser(employerId, clearedData);
            alert("Company Profile Deleted/Cleared Successfully");
            loadCompany();
        }
        catch (error) {
            console.log(error);
            alert("Failed to delete company profile details");
        }

    }

    return (

        <div className="company-profile-page">

            <h1>

                Company Profile

            </h1>

            <form

                className="company-form"

                onSubmit={updateProfile}

            >

                <div className="form-group">

                    <label>

                        Company Name

                    </label>

                    <input

                        type="text"

                        name="fullName"

                        value={company.fullName}

                        onChange={handleChange}

                        required

                    />

                </div>

                <div className="form-group">

                    <label>

                        Email

                    </label>

                    <input

                        type="email"

                        name="email"

                        value={company.email}

                        onChange={handleChange}

                        required

                    />

                </div>

                <div className="form-group">

                    <label>

                        Phone Number

                    </label>

                    <input

                        type="text"

                        name="phone"

                        value={company.phone}

                        onChange={handleChange}

                    />

                </div>

                <div className="form-group">

                    <label>

                        Address

                    </label>

                    <input

                        type="text"

                        name="address"

                        value={company.address}

                        onChange={handleChange}

                    />

                </div>

                <div className="form-group">

                    <label>

                        Website

                    </label>

                    <input

                        type="text"

                        name="website"

                        value={company.website}

                        onChange={handleChange}

                    />

                </div>

                <div className="form-group full-width">

                    <label>

                        Company Description

                    </label>

                    <textarea

                        rows="5"

                        name="description"

                        value={company.description}

                        onChange={handleChange}

                    />

                </div>

                <div className="button-group">

                    <button type="submit">

                        Update Profile

                    </button>

                    <button type="button" className="delete-btn" onClick={deleteProfile} style={{ marginLeft: "10px", backgroundColor: "#dc3545", color: "white" }}>

                        Delete Profile

                    </button>

                </div>

            </form>

        </div>

    );

}
