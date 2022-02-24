import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import HomeApplication from "./client/home/HomeApplication";
import FilterApplication from "./client/hotel/filter/FilterApplication";


export default function MainApp() {


  return (
    <Router>
        <Routes>
          <Route exact path="/" element={<HomeApplication />}/>
		  <Route exact path="/client/hotel/filter" element={<FilterApplication />}/>
        </Routes>
    </Router>
  );
}
