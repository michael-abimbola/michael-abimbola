import './ImageUpload.css';
import axios from "axios"
import { useEffect, useState } from "react";
import jwt_decode from 'jwt-decode'


const UploadImage = () => {
    const [baseImage, setBaseImage] = useState("https://hageglede.no/wp-content/uploads/2020/03/Screenshot-2020-03-14-at-15.22.46-300x211.png")
    const [selectedFile, setSelectedFile] = useState(null)
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)
    const [gradYears, setGradYears] = useState([])
    const [gradClasses, setGradClasses] = useState([])

    const [gradYearID, setGradYearID] = useState(0)
    const [gradClassID, setGradClassID] = useState(0)
    const [category, setCategory] = useState("")

    const user = localStorage.getItem("jwtToken")
    let decoded = jwt_decode(user)
    localStorage.setItem("role", decoded.role);
    const userRole = localStorage.getItem("role");

    const onChange = (e) => {
        const file = e.target.files[0];
        setSelectedFile(file);
        const base64 = convertBase64(file);
        base64.then((res) => {
            setBaseImage(res);
            // console.log(res)
        })

    }

    const convertBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(file);

            fileReader.onload = () => {
                resolve(fileReader.result)
            };

            fileReader.onerror = (error) => {
                reject(error)
            }
        })
    }

    const handleSubmit = (() => {
        setCount(count + 1)
        alert("sent")
    })

    useEffect(() => {
        if (selectedFile != null) {
            const fd = new FormData();
            fd.append("image", selectedFile)
            axios.post(`/image/upload/${gradYearID}/${gradClassID}/${category}`, fd, {
                headers: {
                    "Authorization": `Bearer ${user}`
                }
            })
                .then((res) => {
                    if (res.data == 200) {
                        setSuccess(true)
                    }
                })
        }
    }, [count])

    useEffect(() => {
        axios.get("graduatingyear", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradYears(res.data)
                console.log(res.data)
            })
            .catch((err) => {
                console.log(err + "gradyear error")
            })
    })

    useEffect(() => {
        axios.get("graduatingclass", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradClasses(res.data)
            })
            .catch((err) => {
                console.log(err)
            })
    })



    return (
        <div id="imageUploaderContainer">
            <div class="uploader">
                <div class="title">
                    <h1>Select and Upload Image</h1>
                </div>

                <div className="image-preview">
                    <img src={baseImage} height="300px" />
                </div>
                <h3>Drag and drop your images into the blue space or choose by clicking the choose file button</h3>
                <div class="file-upload">
                    <input type="file" clasName="imageForm" name="file" onChange={onChange} />
                </div>

                <div class="selecterContainer">
                    <div class="gradYearSelect">
                        <select onChange={(e) => setGradYearID(e.target.value)}>
                            <option>GradYears</option>
                            {gradYears.map((item) =>
                                <option value={item.id}>{item.graduatingYear}</option>)}
                        </select>
                    </div>

                    <div class="gradClassSelect">
                        <select onChange={(e) => setGradClassID(e.target.value)}>
                            <option>GradClasses</option>
                            {gradClasses.map((item) =>
                                <option value={item.id}>{item.className}</option>)}
                        </select>
                    </div>
                    {userRole == "STUDENT" &&
                        <div class="imageCategorySelect">
                            <select onChange={(e) => setCategory(e.target.value)}>
                                <option>SportEvents</option>
                                <option>MemorableDays</option>
                            </select>
                        </div>
                    }
                    {userRole == "TEACHER" &&
                        <div class="imageCategorySelect">
                            <select onChange={(e) => setCategory(e.target.value)}>
                                <option>SportEvents</option>
                                <option>Top students of the year</option>
                                <option>StudentAwards</option>
                            </select>
                        </div>
                    }
                    {userRole == "YBCM" &&
                        <div class="imageCategorySelect">
                            <select onChange={(e) => setCategory(e.target.value)}>
                                <option>SportEvents</option>
                                <option>Top students of the year</option>
                                <option>StudentAwards</option>
                                <option>MemorableDays</option>
                            </select>
                        </div>
                    }
                    {userRole == "ADMIN" &&
                        <div class="imageCategorySelect">
                            <select onChange={(e) => setCategory(e.target.value)}>
                                <option>SportEvents</option>
                                <option>Top students of the year</option>
                                <option>StudentAwards</option>
                                <option>MemorableDays</option>
                            </select>
                        </div>
                    }
                </div>
                <div class="upload-image">
                    <button onClick={handleSubmit}>Upload</button>
                </div>

            </div>
            {success && <h4 className="success">Image Uploaded successfully!</h4>}
        </div>
    );
}

export default UploadImage;