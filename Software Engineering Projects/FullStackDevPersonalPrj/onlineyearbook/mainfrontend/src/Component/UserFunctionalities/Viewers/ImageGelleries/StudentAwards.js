import axios from "axios"
import { useEffect, useState } from "react"
import './ViewImage.css';
const StudentAwards = () => {
    let gradYerID = localStorage.getItem("guestChosenGradYear")
    let category = "StudentAwards"
    const [image, setImage] = useState([])
    const user = localStorage.getItem("jwtToken")
    useEffect(() => {
        axios.get(`image/getAll/${gradYerID}/${category}`, {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setImage(res.data)
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])
    return (
        <div>
            <h1>STUDENT AWARDS GALLERY</h1>
            <div className="grid-container">
                {image.map((item) => <div className="imageholder"><img src={`data:image/PNG;base64,${item.content}`} height="200px" alt="" /></div>)}
            </div>
        </div>
    )
}

export default StudentAwards;