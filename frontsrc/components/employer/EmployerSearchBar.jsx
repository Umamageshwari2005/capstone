import { useState } from "react";

import "../../css/employerDashboard.css";

export default function EmployerSearchBar({

    onSearch

}) {

    const [keyword,setKeyword]=useState("");

    function search(e){

        const value=e.target.value;

        setKeyword(value);

        onSearch(value);

    }

    return(

        <div className="employer-search">

            <input

                type="text"

                placeholder="Search Posted Jobs"

                value={keyword}

                onChange={search}

            />

        </div>

    );

}
