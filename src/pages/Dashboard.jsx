import "../css/dashboard.css";

export default function Dashboard() {

    return (

        <div>

            <h2>

                Search Jobs

            </h2>

            <input

                className="search"

                placeholder="Search jobs..."

            />

            <div className="cards">

                <div className="card">

                    Recommended Jobs

                </div>

                <div className="card">

                    Recent Applications

                </div>

            </div>

        </div>

    );

}
