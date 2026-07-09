import "../../css/employerDashboard.css";

export default function WelcomeEmployer() {

    const fullName = sessionStorage.getItem("fullName");

    return (

        <div className="employer-welcome">

            <div>

                <h1>

                    Welcome, {fullName}

                </h1>

                <p>

                    Start posting Jobs and start Hiring

                </p>

            </div>

        </div>

    );

}
