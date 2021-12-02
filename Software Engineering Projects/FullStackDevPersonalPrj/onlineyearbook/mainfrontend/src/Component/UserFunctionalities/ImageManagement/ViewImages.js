import './ViewImage.css';
import axios from "axios"
import { useEffect, useState } from "react"
import jwt_decode from 'jwt-decode'

const ImageView = () => {
    const [viewImages, setViewImages] = useState([])
    const [image, setImage] = useState([])
    const user = localStorage.getItem("jwtToken")
    let decoded = jwt_decode(user)
    localStorage.setItem("role", decoded.role);
    localStorage.setItem("usr", decoded.sub);
    const userRole = localStorage.getItem("role");
    const userName = localStorage.getItem("usr");
    useEffect(() => {
        axios.get("/image/getAll", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setImage(res.data)
            })
    })

    return (
        <div>
            <h1>Welcome  {userRole} to Memories Gallery</h1>
            <div className="grid-container">
                {image.map((item) => <div className="imageholder"><img src={`data:image/PNG;base64,${item.content}`} height="200px" alt="" /></div>)}
            </div>
        </div>
    );
}

export default ImageView;