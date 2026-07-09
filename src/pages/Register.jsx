import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import AuthService from "../services/AuthService";

export default function Register() {
    const navigate = useNavigate();

    const [user, setUser] = useState({
        fullName: "",
        email: "",
        password: "",
        phone: "",
        role: "JOB_SEEKER"
    });

    const [showPassword, setShowPassword] = useState(false);

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const passwordRegex = /^[A-Z](?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*(),.?":{}|<>]).*$/;
        if (!passwordRegex.test(user.password)) {
            alert("Password must start with an uppercase letter, contain at least one special character, one number, and letters.");
            return;
        }

        try {
            await AuthService.register(user);
            alert("Registration Successful");
            navigate("/login");
        } catch (error) {
            alert("Registration Failed");
            console.log(error);
        }
    };

    return (
        <div style={{
            background: "linear-gradient(135deg, #0f0b29 0%, #1e1b4b 50%, #312e81 100%)",
            minHeight: "100vh",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            padding: "20px"
        }}>
            <div className="card shadow border-0 rounded-4" style={{ width: "100%", maxWidth: "500px", backgroundColor: "rgba(255, 255, 255, 0.95)", backdropFilter: "blur(10px)" }}>
                <div className="card-header border-0 text-white rounded-top-4 p-4 text-center" style={{ background: "linear-gradient(135deg, #10b981, #059669)" }}>
                    <h3 className="fw-bold mb-1">Create Account</h3>
                    <p className="mb-0 fs-14 text-white-50">Join CareerCrafter Platform</p>
                </div>
                
                <div className="card-body p-4">
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label fw-bold text-secondary">Full Name</label>
                            <input
                                type="text"
                                className="form-control rounded-3 p-2.5"
                                name="fullName"
                                value={user.fullName}
                                onChange={handleChange}
                                required
                                placeholder="John Doe"
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label fw-bold text-secondary">Email Address</label>
                            <input
                                type="email"
                                className="form-control rounded-3 p-2.5"
                                name="email"
                                value={user.email}
                                onChange={handleChange}
                                required
                                placeholder="name@company.com"
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label fw-bold text-secondary">Password</label>
                            <div className="position-relative">
                                <input
                                    type={showPassword ? "text" : "password"}
                                    className="form-control rounded-3 p-2.5"
                                    name="password"
                                    value={user.password}
                                    onChange={handleChange}
                                    required
                                    placeholder="••••••••"
                                    style={{ paddingRight: "40px" }}
                                />
                                <button
                                    type="button"
                                    onClick={() => setShowPassword(!showPassword)}
                                    className="position-absolute end-0 top-50 translate-middle-y border-0 bg-transparent"
                                    style={{ cursor: "pointer", right: "12px", zIndex: 10, padding: "0 10px" }}
                                >
                                    {showPassword ? <FaEyeSlash /> : <FaEye />}
                                </button>
                            </div>
                            <small className="form-text text-muted" style={{ fontSize: "11px", display: "block", marginTop: "4px" }}>
                                * Password must start with an uppercase letter, and contain at least one special character, number, and letter.
                            </small>
                        </div>

                        <div className="mb-3">
                            <label className="form-label fw-bold text-secondary">Phone Number</label>
                            <input
                                type="text"
                                className="form-control rounded-3 p-2.5"
                                name="phone"
                                value={user.phone}
                                onChange={handleChange}
                                required
                                placeholder="10-digit mobile number"
                            />
                        </div>

                        <div className="mb-4">
                            <label className="form-label fw-bold text-secondary">Role / Account Type</label>
                            <select
                                className="form-select rounded-3 p-2.5"
                                name="role"
                                value={user.role}
                                onChange={handleChange}
                            >
                                <option value="JOB_SEEKER">Job Seeker (Candidate)</option>
                                <option value="EMPLOYER">Employer (Recruiter)</option>
                            </select>
                        </div>

                        <button type="submit" className="btn btn-success text-white w-100 p-2.5 rounded-3 fw-bold shadow-sm">
                            Register Account
                        </button>
                    </form>
                    
                    <hr className="my-4" />
                    <p className="text-center text-muted mb-0 fs-14">
                        Already have an account?{" "}
                        <Link to="/login" style={{ color: "#10b981", fontWeight: "600", textDecoration: "none" }}>
                            Login Here
                        </Link>
                    </p>
                </div>
            </div>
        </div>
    );
}
