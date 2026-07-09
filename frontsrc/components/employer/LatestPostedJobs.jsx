import "../../css/employerDashboard.css";

export default function LatestPostedJobs({ jobs }) {
    const latestThree = (jobs || []).slice(0, 3);

    return (
        <div className="latest-posted-jobs card shadow-sm p-4 bg-white rounded-4 border-0 mb-4" style={{ borderLeft: "5px solid #2563eb" }}>
            <h2 className="fs-4 fw-bold border-bottom pb-2 mb-4" style={{ color: "#2563eb" }}>Recent Posted Jobs</h2>

            {latestThree.length === 0 ? (
                <div className="empty-box text-muted text-center py-4">No Jobs Posted Yet</div>
            ) : (
                <div className="row row-cols-1 row-cols-md-3 g-3">
                    {latestThree.map(job => (
                        <div key={job.jobId} className="col">
                            <div className="card h-100 border-0 rounded-4 p-3 shadow-sm hover-shadow transition" style={{ backgroundColor: "#f8fafc" }}>
                                <div className="card-body p-0 d-flex flex-column justify-content-between">
                                    <div>
                                        <div className="d-flex justify-content-between align-items-start mb-2">
                                            <h3 className="card-title fs-5 fw-bold mb-1" style={{ color: "#2563eb" }}>{job.jobTitle}</h3>
                                            <span className="badge text-bg-primary rounded-pill px-2 py-1 fs-12">{job.jobType}</span>
                                        </div>
                                        <h4 className="card-subtitle fs-6 fw-semibold text-secondary mb-3">{job.companyName}</h4>
                                        
                                        <div className="d-flex flex-column gap-1 mb-3 text-muted fs-13">
                                            <span>📍 {job.location}</span>
                                            <span>💼 {job.experience}</span>
                                            <span>💰 ₹ {job.salary} LPA</span>
                                        </div>
                                    </div>

                                    <div className="mt-auto p-2 rounded-3" style={{ backgroundColor: "#e2e8f0", border: "1px dashed #94a3b8" }}>
                                        <div className="fs-12 mb-1">
                                            <strong>Skills:</strong> <span className="text-secondary">{job.requiredSkills}</span>
                                        </div>
                                        <div className="fs-11 text-muted border-top pt-1 mt-1">
                                            <strong>ID:</strong> {job.jobId} | <strong>Date:</strong> {job.postedDate || "N/A"}
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
