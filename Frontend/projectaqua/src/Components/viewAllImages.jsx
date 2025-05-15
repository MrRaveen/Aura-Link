/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import '../CSS/index.css';
import { useNavigate, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownload, faTimes, faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import StickyNavbar from '../Components/navBar.jsx';

function ViewAllImages() {
    const location = useLocation();
    const navigate = useNavigate();
    const receivedData = location.state;
    const [currentImage, setCurrentImage] = useState(0);
    const [isFullscreen, setIsFullscreen] = useState(false);

    // Handle image download
    const handleDownload = async (mediaName) => {
        try {
            const response = await fetch(`http://127.0.0.1:10000/devstoreaccount1/postcontents/${mediaName}`);
            const blob = await response.blob();
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = downloadUrl;
            link.setAttribute('download', mediaName);
            document.body.appendChild(link);
            link.click();
            link.remove();
        } catch (error) {
            console.error('Download failed:', error);
        }
    };

    // Navigation controls
    const handleNext = () => {
        setCurrentImage(prev => (prev + 1) % receivedData.length);
    };

    const handlePrev = () => {
        setCurrentImage(prev => (prev - 1 + receivedData.length) % receivedData.length);
    };

    return (
        <div>
            <StickyNavbar></StickyNavbar>
        <div className={`${isFullscreen ? 'fixed inset-0 z-50 bg-black' : 'min-h-screen'} p-4 bg-gray-100`}>
            {/* Header */}
            <div className="flex justify-between items-center mb-6 p-4 bg-white rounded-lg shadow-md sticky top-0 z-10">
                <button 
                    onClick={() => navigate(-1)}
                    className="flex items-center gap-2 text-white-500 bg-blue-400"
                >
                    <FontAwesomeIcon icon={faArrowLeft} />
                    <span>Back to Gallery</span>
                </button>
                <div className="text-gray-600">
                    Showing {receivedData.length} images
                </div>
            </div>

            {/* Main Image Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                {receivedData.map((item, index) => (
                    <div 
                        key={item.contentid}
                        className="relative group bg-white rounded-xl shadow-lg hover:shadow-xl transition-shadow duration-300"
                    >
                        <img
                            src={`http://127.0.0.1:10000/devstoreaccount1/postcontents/${item.media_name}`}
                            alt={`Content ${index + 1}`}
                            className="w-full h-64 object-cover rounded-t-xl cursor-zoom-in"
                            onClick={() => {
                                setCurrentImage(index);
                                setIsFullscreen(true);
                            }}
                        />
                        
                        {/* Image Overlay Controls */}
                        <div className="absolute inset-0 bg-black bg-opacity-0 group-hover:bg-opacity-40 transition-all duration-300 flex items-center justify-center opacity-0 group-hover:opacity-100">
                            <button
                                onClick={() => handleDownload(item.media_name)}
                                className="p-3 bg-white rounded-full shadow-lg hover:bg-gray-100 transition-colors"
                            >
                                <FontAwesomeIcon icon={faDownload} className="text-blue-600 text-xl" />
                            </button>
                        </div>

                        {/* Image Metadata */}
                        <div className="p-4">
                            <div className="flex justify-between items-center text-sm text-gray-600">
                                <span>Size: {item.meta_data.size}</span>
                                <span>{item.type}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            {/* Fullscreen Viewer */}
            {isFullscreen && (
                <div className="fixed inset-0 bg-black bg-opacity-90 flex items-center justify-center p-4 z-50">
                    <button
                        onClick={() => setIsFullscreen(false)}
                        className="absolute top-4 right-4 text-white text-2xl p-2 hover:text-gray-300"
                    >
                        <FontAwesomeIcon icon={faTimes} />
                    </button>
                    
                    <div className="relative max-w-4xl w-full">
                        <img
                            src={`http://127.0.0.1:10000/devstoreaccount1/postcontents/${receivedData[currentImage].media_name}`}
                            alt="Fullscreen view"
                            className="max-h-[90vh] w-full object-contain"
                        />
                        
                        {/* Navigation Arrows */}
                        <button
                            onClick={handlePrev}
                            className="absolute left-4 top-1/2 -translate-y-1/2 bg-white p-4 rounded-full shadow-lg hover:bg-gray-100"
                        >
                            ❮
                        </button>
                        <button
                            onClick={handleNext}
                            className="absolute right-4 top-1/2 -translate-y-1/2 bg-white p-4 rounded-full shadow-lg hover:bg-gray-100"
                        >
                            ❯
                        </button>
                        
                        {/* Image Counter */}
                        <div className="absolute bottom-4 left-1/2 -translate-x-1/2 bg-black bg-opacity-50 text-white px-4 py-2 rounded-full">
                            {currentImage + 1} / {receivedData.length}
                        </div>
                    </div>
                </div>
            )}
        </div>
        </div>
    );
}

export default ViewAllImages;