import StickyNavbar from '../Components/navBar.jsx';
import { useState } from 'react';
function NotificationPage(){
const [notifications, setNotifications] = useState([
    {
      id: 1,
      user: 'johndoe',
      userImage: 'http://127.0.0.1:10000/devstoreaccount1/postcontents/CONT20.jpg',
      type: 'like',
      postImage: 'http://127.0.0.1:10000/devstoreaccount1/postcontents/CONT20.jpg',
      timestamp: new Date(Date.now() - 3600000),
      read: false
    },
    {
      id: 2,
      user: 'janedoe',
      userImage: 'http://127.0.0.1:10000/devstoreaccount1/postcontents/CONT20.jpg',
      type: 'comment',
      postImage: 'http://127.0.0.1:10000/devstoreaccount1/postcontents/CONT20.jpg',
      timestamp: new Date(Date.now() - 7200000),
      read: true
    },
    {
      id: 3,
      user: 'mike_smith',
      userImage: 'http://127.0.0.1:10000/devstoreaccount1/postcontents/CONT20.jpg',
      type: 'follow',
      timestamp: new Date(Date.now() - 10800000),
      read: false
    },
  ]);

  const markAsRead = (id) => {
    setNotifications(notifications.map(notification => 
      notification.id === id ? { ...notification, read: true } : notification
    ));
  };

  const markAllAsRead = () => {
    setNotifications(notifications.map(notification => ({
      ...notification,
      read: true
    })));
  };

  return (
   <div>
    <StickyNavbar></StickyNavbar>
     <div className="max-w-2xl mx-auto p-4">
      <div className="flex justify-between items-center mb-4 pb-2 border-b">
        <h2 className="text-xl font-bold">Notifications</h2>
        <button 
          onClick={markAllAsRead}
          className="text-blue-500 font-semibold hover:bg-blue-50 px-4 py-2 rounded-lg transition-colors"
        >
          Mark all as read
        </button>
      </div>

      <div className="space-y-3">
        {notifications.map(notification => (
          <div 
            key={notification.id}
            className={`flex items-center p-3 rounded-xl cursor-pointer hover:bg-gray-50 ${
              !notification.read ? 'bg-gray-50' : ''
            } transition-colors`}
            onClick={() => markAsRead(notification.id)}
          >
            <img 
              src={notification.userImage}
              alt={notification.user}
              className="w-11 h-11 rounded-full object-cover"
            />
            
            <div className="ml-3 flex-1">
              <p className="text-sm">
                <span className="font-semibold">{notification.user}</span>
                {notification.type === 'like' && ' liked your post'}
                {notification.type === 'comment' && ' commented on your post'}
                {notification.type === 'follow' && ' started following you'}
                {!notification.read && (
                  <span className="w-2 h-2 bg-blue-500 rounded-full ml-2 inline-block"></span>
                )}
              </p>
              <span className="text-xs text-gray-500">
                {new Date(notification.timestamp).toLocaleTimeString('en-US', {
                  hour: '2-digit',
                  minute: '2-digit'
                })}
              </span>
            </div>

            {notification.type !== 'follow' && (
              <img 
                src={notification.postImage}
                alt="Post"
                className="w-11 h-11 rounded-lg object-cover ml-auto"
              />
            )}
          </div>
        ))}
      </div>
    </div>
   </div>
  );
}
export default NotificationPage;