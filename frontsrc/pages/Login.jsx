import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import AuthService from "../services/AuthService";
import "../css/login.css";

export default function Login() {
    const navigate = useNavigate();

    const [loginData, setLoginData] = useState({
        email: "",
        password: "",
        role: "JOB_SEEKER"
    });

    const [showForgot, setShowForgot] = useState(false);
    const [forgotData, setForgotData] = useState({
        email: "",
        newPassword: "",
        confirmPassword: ""
    });

    const [showPassword, setShowPassword] = useState(false);
    const [showNewPassword, setShowNewPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const handleChange = (e) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        });
    };

    const handleForgotChange = (e) => {
        setForgotData({
            ...forgotData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await AuthService.login(loginData);
            sessionStorage.setItem("token", response.data.token);
            sessionStorage.setItem("role", response.data.role);
            sessionStorage.setItem("userId", response.data.userId);
            sessionStorage.setItem("fullName", response.data.fullName);

            alert("Login Successful");

            if (response.data.role === "EMPLOYER") {
                navigate("/employer-dashboard");
            } else {
                navigate("/user-dashboard");
            }
        } catch (error) {
            alert("Invalid Email, Password or Role");
        }
    };

    const handleForgotPasswordSubmit = async (e) => {
        e.preventDefault();
        
        const passwordRegex = /^[A-Z](?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*(),.?":{}|<>]).*$/;
        if (!passwordRegex.test(forgotData.newPassword)) {
            alert("Password must start with an uppercase letter, contain at least one special character, one number, and letters.");
            return;
        }

        if (forgotData.newPassword !== forgotData.confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        try {
            await AuthService.forgotPassword({
                email: forgotData.email,
                newPassword: forgotData.newPassword
            });
            alert("Password reset successfully. You can now login with your new password.");
            setShowForgot(false);
            setForgotData({ email: "", newPassword: "", confirmPassword: "" });
            setShowNewPassword(false);
            setShowConfirmPassword(false);
        } catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Failed to reset password";
            alert("Failed to reset password: " + msg);
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
            <div className="card shadow border-0 rounded-4" style={{ width: "100%", maxWidth: "450px", backgroundColor: "rgba(255, 255, 255, 0.95)", backdropFilter: "blur(10px)" }}>
                {!showForgot ? (
                    <>
                        <div className="card-header border-0 text-white rounded-top-4 p-4 text-center" style={{ background: "linear-gradient(135deg, #4f46e5, #3b82f6)" }}>
                            <h3 className="fw-bold mb-1">CareerCrafter</h3>
                            <p className="mb-0 fs-14 text-white-50">Log in to your workspace</p>
                        </div>
                        <div className="card-body p-4">
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label className="form-label fw-bold text-secondary">Email Address</label>
                                    <input
                                        type="email"
                                        className="form-control rounded-3 p-2.5"
                                        name="email"
                                        value={loginData.email}
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
                                            value={loginData.password}
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
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold text-secondary">Role / Account Type</label>
                                    <select
                                        className="form-select rounded-3 p-2.5"
                                        name="role"
                                        value={loginData.role}
                                        onChange={handleChange}
                                    >
                                        <option value="JOB_SEEKER">Job Seeker (Candidate)</option>
                                        <option value="EMPLOYER">Employer (Recruiter)</option>
                                    </select>
                                </div>

                                <div className="text-end mb-3">
                                    <a href="#" onClick={(e) => { e.preventDefault(); setShowForgot(true); }} style={{ color: "#4f46e5", textDecoration: "none", fontSize: "14px", fontWeight: "600" }}>
                                        Forgot Password?
                                    </a>
                                </div>

                                <button type="submit" className="btn text-white w-100 p-2.5 rounded-3 fw-bold shadow-sm" style={{ background: "linear-gradient(135deg, #4f46e5, #3b82f6)", border: "none" }}>
                                    Sign In
                                </button>
                            </form>
                            
                            <hr className="my-4" />
                            <p className="text-center text-muted mb-0 fs-14">
                                New to CareerCrafter?{" "}
                                <Link to="/register" style={{ color: "#3b82f6", fontWeight: "600", textDecoration: "none" }}>
                                    Register Here
                                </Link>
                            </p>
                        </div>
                    </>
                ) : (
                    <>
                        <div className="card-header border-0 text-white rounded-top-4 p-4 text-center" style={{ background: "linear-gradient(135deg, #ef4444, #f97316)" }}>
                            <h3 className="fw-bold mb-1">Reset Password</h3>
                            <p className="mb-0 fs-14 text-white-50">Enter credentials to recover password</p>
                        </div>
                        <div className="card-body p-4">
                            <form onSubmit={handleForgotPasswordSubmit}>
                                <div className="mb-3">
                                    <label className="form-label fw-bold text-secondary">Email Address</label>
                                    <input
                                        type="email"
                                        className="form-control rounded-3 p-2.5"
                                        name="email"
                                        value={forgotData.email}
                                        onChange={handleForgotChange}
                                        required
                                        placeholder="name@company.com"
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold text-secondary">New Password</label>
                                    <div className="position-relative">
                                        <input
                                            type={showNewPassword ? "text" : "password"}
                                            className="form-control rounded-3 p-2.5"
                                            name="newPassword"
                                            value={forgotData.newPassword}
                                            onChange={handleForgotChange}
                                            required
                                            placeholder="••••••••"
                                            style={{ paddingRight: "40px" }}
                                        />
                                        <button
                                            type="button"
                                            onClick={() => setShowNewPassword(!showNewPassword)}
                                            className="position-absolute end-0 top-50 translate-middle-y border-0 bg-transparent"
                                            style={{ cursor: "pointer", right: "12px", zIndex: 10, padding: "0 10px" }}
                                        >
                                            {showNewPassword ? <FaEyeSlash /> : <FaEye />}
                                        </button>
                                    </div>
                                    <small className="form-text text-muted" style={{ fontSize: "11px", display: "block", marginTop: "4px" }}>
                                        * Password must start with an uppercase letter, and contain at least one special character, number, and letter.
                                    </small>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold text-secondary">Confirm New Password</label>
                                    <div className="position-relative">
                                        <input
                                            type={showConfirmPassword ? "text" : "password"}
                                            className="form-control rounded-3 p-2.5"
                                            name="confirmPassword"
                                            value={forgotData.confirmPassword}
                                            onChange={handleForgotChange}
                                            required
                                            placeholder="••••••••"
                                            style={{ paddingRight: "40px" }}
                                        />
                                        <button
                                            type="button"
                                            onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                            className="position-absolute end-0 top-50 translate-middle-y border-0 bg-transparent"
                                            style={{ cursor: "pointer", right: "12px", zIndex: 10, padding: "0 10px" }}
                                        >
                                            {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
                                        </button>
                                    </div>
                                </div>

                                <button type="submit" className="btn btn-danger w-100 p-2.5 rounded-3 fw-bold shadow-sm mb-3">
                                    Update Password
                                </button>
                                
                                <button type="button" onClick={() => setShowForgot(false)} className="btn btn-light w-100 p-2.5 rounded-3 fw-semibold border border-light-subtle">
                                    Back to Login
                                </button>
                            </form>
                        </div>
                    </>
                )}
            </div>
        </div>
    );
}
