/* eslint-disable no-unused-vars */
"use client"
import { useState } from "react"
import "bootstrap/dist/css/bootstrap.min.css"
import "../CSS/index.css"
import StickyNavbar from "../Components/navBar.jsx"
import createPostCont from '../Controller/createPostCont.jsx';
import getIDprocess from '../Controller/getTheuserID';

const CreatePost = () => {
  const userID = getIDprocess();
  //create post handler
  const createPostContHanler = () => {
    //var title = document.getElementById('titlePost').value;
    createPostCont(title,description,selectedImages,userID);
  }  

  const [selectedImages, setSelectedImages] = useState([])
  const [title, setTitle] = useState("")
  const [description, setDescription] = useState("")

  const handleImageSelect = (event) => {
    const files = Array.from(event.target.files)

    files.forEach((file) => {
      if (file.type.startsWith("image/")) {
        const reader = new FileReader()
        reader.onload = (e) => {
          const newImage = {
            id: Date.now() + Math.random(),
            file: file,
            preview: e.target.result,
            name: file.name,
          }//create the new image
          setSelectedImages((prev) => [...prev, newImage])
          //(prev)=> ...prev copies all elements from the existing prev array (the current state) into a new array.
          //[...prev, newImage] create a new array with the previous contents and append the new content
        }
        reader.readAsDataURL(file)
      }
    })

    // Reset the input value so the same file can be selected again
    event.target.value = ""
  }

  const removeImage = (imageId) => {
    setSelectedImages((prev) => prev.filter((img) => img.id !== imageId))
  }

  const triggerFileInput = () => {
    document.getElementById("imageInput").click()
  }

  return (
    <div className="bg-gradient-to-r from-blue-500 to-purple-500 h-full">
      <StickyNavbar></StickyNavbar>
      <div className="flex items-center justify-center m-2">
        <div className="shadow h-auto w-200 bg-gray-50 text-center rounded p-6">
          <h1 className="mb-4 text-2xl font-bold">Create your post</h1>

          <div className="mb-2">
            <label className="block text-left mb-2 font-medium">Title</label><br />
            <input
              type="text"
              placeholder="Example title"
              id="titlePost"
              value={title}
              maxLength="80"
              onChange={(e) => setTitle(e.target.value)}
              className="p-2 mt-1 w-150 rounded-md bg-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            />
            <br />
            <label>80/ {title.length}</label>
          </div>

          <div className="mb-2">
            <label className="block text-left mb-2 font-medium">Description</label><br />
            <textarea
              id="desInput"
              className="mt-1 rounded-md bg-gray-300 w-150 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm p-2"
              placeholder="Some description"
              rows={4}
              maxLength="400"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
            <br />
            <label>400/{description.length}</label>
          </div>
          <div className="mb-2">
            <label className="block text-left mb-2 font-medium">Images (Optional)</label><br />
            <button
              type="button"
              onClick={triggerFileInput}
              className="rounded bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-4 rounded-md transition-colors duration-200"
            >
              Add Images
            </button>
            <input
              id="imageInput"
              type="file"
              accept="image/*"
              multiple
              onChange={handleImageSelect}
              className="hidden"
            />
          </div>

          {selectedImages.length > 0 && (
            <div className="mb-4">
              <h3 className="text-left mb-3 font-medium">Image Previews:</h3>
              <div className="grid grid-cols-2 gap-4">
                {selectedImages.map((image) => (
                  <div key={image.id} className="relative">
                    <img
                      src={image.preview || "/placeholder.svg"}
                      alt={image.name}
                      className="w-full h-32 object-cover rounded-md border-2 border-gray-300"
                    />
                    <button
                      onClick={() => removeImage(image.id)}
                      className="rounded absolute top-1 right-1 bg-red-500 hover:bg-red-600 text-white rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold"
                      title="Remove image"
                    >
                      ×
                    </button>
                    <p className="text-xs mt-1 text-gray-600 truncate">{image.name}</p>
                  </div>
                ))}
              </div>
            </div>
          )}

          <div className="mt-6">
            <button
              type="submit"
              className="rounded bg-green-600 hover:bg-green-700 text-white font-medium py-2 px-6 rounded-md transition-colors duration-200"
              onClick={()=>createPostContHanler()}
            >
              Create Post
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default CreatePost
