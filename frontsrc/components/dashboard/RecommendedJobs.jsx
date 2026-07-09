import "../../css/latestJobs.css";

export default function RecommendedJobs({ jobs = [] }) {
    return (
        <div className="recommended-jobs-card shadow-sm p-4 bg-white rounded-4 border-0 mb-4" style={{ borderLeft: "5px solid #10b981" }}>
            <h2 className="fs-4 fw-bold border-bottom pb-2 mb-4" style={{ color: "#10b981" }}>Recommended Jobs For You</h2>

            {jobs.length === 0 ? (
                <div className="text-muted text-center py-4">No Recommendations Found. Try updating your skills or profile!</div>
            ) : (
                <div className="row row-cols-1 row-cols-md-3 g-3">
                    {jobs.map(job => (
                        <div key={job.jobId} className="col">
                            <div className="card h-100 border-0 rounded-4 p-3 shadow-sm hover-shadow transition" style={{ backgroundColor: "#f0fdf4" }}>
                                <div className="card-body p-0 d-flex flex-column justify-content-between">
                                    <div>
                                        <div className="d-flex justify-content-between align-items-start mb-2">
                                            <h3 className="card-title fs-5 fw-bold mb-1" style={{ color: "#10b981" }}>{job.jobTitle}</h3>
                                            <span className="badge rounded-pill px-2 py-1 fs-12 text-bg-success">{job.jobType}</span>
                                        </div>
                                        <h4 className="card-subtitle fs-6 fw-semibold text-secondary mb-3">{job.companyName}</h4>
                                        
                                        <div className="d-flex flex-column gap-1 mb-3 text-muted fs-13">
                                            <span>📍 {job.location}</span>
                                            <span>💼 {job.experience}</span>
                                            <span>💰 ₹ {job.salary} LPA</span>
                                        </div>
                                    </div>

                                    <div className="mt-auto p-2 rounded-3" style={{ backgroundColor: "#dcfce7", border: "1px dashed #86efac" }}>
                                        <div className="fs-12">
                                            <strong>Skills:</strong> <span className="text-secondary">{job.requiredSkills}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
