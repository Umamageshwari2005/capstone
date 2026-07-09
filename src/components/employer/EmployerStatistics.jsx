import "../../css/dashboard.css";
import {
    FaBriefcase,
    FaClipboardList,
    FaTimesCircle
} from "react-icons/fa";

export default function EmployerStatistics({
    totalJobs,
    applicants,
    closed
}) {
    return (
        <div className="stats-grid" style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gap: "20px", marginBottom: "30px" }}>
            {/* Total Posted Jobs */}
            <div
                className="stat-card jobs"
                style={{
                    background: "linear-gradient(135deg,#9CC6F5,#6EA8F7)",
                    color: "white",
                    borderRadius: "28px",
                    position: "relative",
                    overflow: "hidden",
                    padding: "25px",
                    boxShadow: "0 8px 20px rgba(0,0,0,0.05)"
                }}
            >
                <FaBriefcase
                    className="stat-icon"
                    style={{ color: "#2563EB", fontSize: "42px", float: "right" }}
                />
                <h4 style={{ margin: "0 0 10px 0", fontSize: "1.1rem", fontWeight: "600" }}>Total Posted Jobs</h4>
                <h2 style={{ margin: 0, fontSize: "2.5rem", fontWeight: "bold" }}>{totalJobs}</h2>
            </div>

            {/* Applicants */}
            <div
                className="stat-card apps"
                style={{
                    background: "linear-gradient(135deg,#9EF0B8,#5AD98B)",
                    color: "white",
                    borderRadius: "28px",
                    position: "relative",
                    overflow: "hidden",
                    padding: "25px",
                    boxShadow: "0 8px 20px rgba(0,0,0,0.05)"
                }}
            >
                <FaClipboardList
                    className="stat-icon"
                    style={{ color: "#15803d", fontSize: "42px", float: "right" }}
                />
                <h4 style={{ margin: "0 0 10px 0", fontSize: "1.1rem", fontWeight: "600" }}>Applicants</h4>
                <h2 style={{ margin: 0, fontSize: "2.5rem", fontWeight: "bold" }}>{applicants}</h2>
            </div>

            {/* Closed */}
            <div
                className="stat-card closed"
                style={{
                    background: "linear-gradient(135deg,#FFE588,#FFC72C)",
                    color: "white",
                    borderRadius: "28px",
                    position: "relative",
                    overflow: "hidden",
                    padding: "25px",
                    boxShadow: "0 8px 20px rgba(0,0,0,0.05)"
                }}
            >
                <FaTimesCircle
                    className="stat-icon"
                    style={{ color: "#b45309", fontSize: "42px", float: "right" }}
                />
                <h4 style={{ margin: "0 0 10px 0", fontSize: "1.1rem", fontWeight: "600" }}>Closed</h4>
                <h2 style={{ margin: 0, fontSize: "2.5rem", fontWeight: "bold" }}>{closed}</h2>
            </div>
        </div>
    );
}