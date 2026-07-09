import "../../css/jobs.css";

export default function SearchJob({
    title,
    setTitle,
    location,
    setLocation,
    company,
    setCompany,
    handleSearch,
    handleClear
}) {

    return (

        <div className="search-job" style={{ display: "flex", gap: "10px", flexWrap: "wrap", marginBottom: "20px", alignItems: "center" }}>

            <input
                type="text"
                placeholder="Search Job Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #ccc" }}
            />

            <input
                type="text"
                placeholder="Search Location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #ccc" }}
            />

            <input
                type="text"
                placeholder="Search Company"
                value={company}
                onChange={(e) => setCompany(e.target.value)}
                style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #ccc" }}
            />

            <button onClick={handleSearch} style={{ padding: "8px 15px", backgroundColor: "#4f46e5", color: "white", border: "none", borderRadius: "6px", cursor: "pointer", fontWeight: "bold" }}>
                Search
            </button>

            <button onClick={handleClear} style={{ padding: "8px 15px", backgroundColor: "#6b7280", color: "white", border: "none", borderRadius: "6px", cursor: "pointer", fontWeight: "bold" }}>
                Clear / View All
            </button>

        </div>

    );

}
