import './ImageViewControl.css';
import axios from "axios"
import { useEffect, useState } from "react"
const ImageViewControl = () => {
    const [unAvailalbleImages, setUnavailableImages] = useState([])

    const [imageID, setImageID] = useState(0)
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)
    const [error, setError] = useState(false)
    const user = localStorage.getItem("jwtToken")
    useEffect(() => {
        axios.get("admin/image/getAll/unavailable", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setUnavailableImages(res.data)
            })
    })


    const handleSubmit = ((e) => {
        alert("approving image ...")
        e.preventDefault()
        fetch(
            `http://localhost:8080/image/?imageid=${imageID}`,
            {
              method: "PUT",
      
              headers: {
                Authorization: `Bearer ${user}`,
                "Access-Control-Allow-Origin": "*",
                "Content-Length": "0",
              },
            }
          )
            .then((res) => {
                if(res.status == 200){
                    setSuccess(true)
                    setError(false)
                }
                else{
                    console.log(res)
                    setError(true)
                    setSuccess(false)
                }
            })
            .catch((error) => {
              console.log(error);
            });
    })

    return (
        <div id="imageViewControlContainer">
            <div class="title">
                <h1>Image Approval Page</h1>
            </div>
            {success && <h4 className="success">Image Approved!</h4>}
            {error && <h4 className="error">Oops something went wrong!</h4>}

            {unAvailalbleImages.map((item) =>
                <div key = {item.id} class="unAvailableImagesContainer">
                    <div class = "unAvailableImage">
                        <img src={`data:image/PNG;base64,${item.content}`} height="200px" alt="" />
                    </div>

                    <form onSubmit = {handleSubmit} class = "unAvailableImageBtn">
                        <button 
                        type = "submit"
                        onClick = {() => setImageID(item.id)}>Make Available</button>
                    </form>
                </div>
            )}

        </div>
    );
}

export default ImageViewControl;