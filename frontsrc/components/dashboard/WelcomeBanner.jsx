import "../../css/dashboard.css";

export default function WelcomeBanner({ user }) {

    return (

        <div className="welcome-banner">

            <div>

                <h1>

                    Welcome {user?.fullName}

                </h1>

                <p>

                    Find your dream job and manage your career with CareerCrafter.

                </p>

            </div>

        </div>

    );

}
