import { Link } from 'react-router-dom';
import jwt_decode from 'jwt-decode'
import reactDom from 'react-dom';
import React, {useContext} from 'react';



const NavBar = () => {
    const user = localStorage.getItem("jwtToken")
    let decoded = jwt_decode(user)
    localStorage.setItem("role", decoded.role);

    const userRole = localStorage.getItem("role");

    const guestRole = localStorage.getItem("guestRole")

    return (
        <div id = "navbarContainer">
            {userRole == "GUEST" && <nav className="navbar">
                <img src={process.env.PUBLIC_URL + "./sch-maryland.png"} height="100px" alt="scndlogo" />
                <Link to="/studentBioView">Students Biography View </Link>
                <Link to= "/topStudents">Top Students gallery</Link>
                <Link to= "/studentAwards">Student Awards gallery</Link>
                <Link to= "/studentsMemorableDays">Student Memorable Days gallery</Link>
                <Link to= "/sportAwards">Sport awards for this year gallery</Link>
            </nav>}

            {userRole == "ADMIN" && <nav className="navbar">
                <img src={process.env.PUBLIC_URL + "./sch-maryland.png"} height="100px" alt="scndlogo" />
                <Link to="/homepage"> Home </Link>
                <Link to="/signup"> Signup Page</Link>
                <Link to="/graduationYear">Manage Grad Year Page</Link>
                <Link to="/graduationClass">Manage Grad Class Page</Link>
                <Link to="/imageUpload">Upload Images</Link>
                <Link to= "/updateProfile"> Update Profile</Link>
                <Link to= "/permitImages">Image Permission page</Link>
            </nav>}

            {userRole == "YBCM" && <nav className="navbar">
                <img src={process.env.PUBLIC_URL + "./sch-maryland.png"} height="100px" alt="scndlogo" />
                <Link to="/homepage"> Home </Link>
                <Link to="/imageView">Memories</Link>
                <Link to="/imageUpload">Upload Images</Link>
                <Link to= "/updateProfile"> Update Profile</Link>
            </nav>}

            {userRole == "STUDENT" && <nav className="navbar">
                <img src={process.env.PUBLIC_URL + "./sch-maryland.png"} height="100px" alt="scndlogo" />
                <Link to = "/studentBio"> Add Student Bio</Link>
                <Link to="/fareWellMessagesView">Farewell Messages</Link>
                <Link to="/imageUpload">Upload Images</Link>
                <Link to= "/updateProfile"> Update Profile</Link>
                <a href = "http://localhost:3050/">Start chatting now in online yearbook public chat room which is only availble to student ;)</a>

            </nav>}

            {userRole == "TEACHER" && <nav className="navbar">
                <img src={process.env.PUBLIC_URL + "./sch-maryland.png"} height="100px" alt="scndlogo" />
                <Link to="/homepage"> Home </Link>
                <Link to = "/farewellMsgAddition">Farewell Message Management</Link>
                {/* <Link to="/graduationYear">Manage Grad Year Page</Link>
                <Link to="/graduationClass">Manage Grad Class Page</Link> */}
                <Link to="/imageUpload">Upload Images</Link>
                <Link to= "/updateProfile"> Update Profile</Link>
            </nav>}
        </div>


    )
}

export default NavBar