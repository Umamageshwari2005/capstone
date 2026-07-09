import { useState } from "react";

import "../../css/searchbar.css";

export default function SearchBar({

    onSearch

}){

    const [keyword,setKeyword]=useState("");

    function search(){

        onSearch(keyword);

    }

    return(

        <div className="search-container">

            <input

                type="text"

                placeholder="Search Job Title..."

                value={keyword}

                onChange={(e)=>setKeyword(e.target.value)}

            />

            <button onClick={search}>

                Search

            </button>

        </div>

    );

}
