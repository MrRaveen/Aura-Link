import { useState, useEffect } from "react";
import StickyNavbar from "../Components/navBar.jsx";
import SettingsProfile from "../Components/settingsCompProfile.jsx";
import "../CSS/index.css";

export default function EditProfile() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const [activeTab, setActiveTab] = useState("Account");

  useEffect(() => {
    document.body.style.overflow = isSidebarOpen ? "hidden" : "auto";
    return () => {
      document.body.style.overflow = "auto";
    };
  }, [isSidebarOpen]);

  const navItems = ["Profile", "Account", "Security", "Notifications", "Privacy"];

  const handleSidebarClick = (item) => {
    setActiveTab(item);
  };

  const renderContent = () => {
    if (activeTab === "Profile") {
      return <SettingsProfile />;
    }
    return (
      <section className="bg-white p-6 rounded-lg shadow">
        <form className="space-y-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                First Name
              </label>
              <input
                type="text"
                placeholder="John"
                className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Last Name
              </label>
              <input
                type="text"
                placeholder="Doe"
                className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Email
            </label>
            <input
              type="email"
              placeholder="john.doe@example.com"
              className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Bio
            </label>
            <textarea
              rows={4}
              placeholder="Tell us about yourself..."
              className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>

          <div className="flex justify-end space-x-4">
            <button
              type="button"
              className="px-5 py-2 border rounded-md text-gray-700 hover:bg-gray-100 transition-colors"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-5 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors focus:ring-2 focus:ring-blue-500 focus:outline-none"
            >
              Save Changes
            </button>
          </div>
        </form>
      </section>
    );
  };

  return (
    <div className="flex flex-col min-h-screen">
      <StickyNavbar />

      <button
        aria-label="Toggle menu"
        className="fixed z-40 top-4 left-4 p-2 bg-white rounded-lg shadow lg:hidden"
        onClick={() => setIsSidebarOpen((prev) => !prev)}
      >
        ☰
      </button>

      <div className="flex flex-1">
        {isSidebarOpen && (
          <div
            className="fixed inset-0 z-30 bg-black/50 lg:hidden"
            onClick={() => setIsSidebarOpen(false)}
          />
        )}

        <aside
          className={`fixed lg:static z-40 top-16 bottom-0 left-0 w-64 bg-gray-800 text-white overflow-y-auto transform transition-transform duration-300 ease-in-out
            ${isSidebarOpen ? "translate-x-0" : "-translate-x-full lg:translate-x-0"}`}
        >
          <div className="p-6 pb-20 space-y-8">
            <button
              aria-label="Close menu"
              className="absolute top-6 right-6 text-2xl lg:hidden"
              onClick={() => setIsSidebarOpen(false)}
            >
              &times;
            </button>

            <h2 className="text-2xl font-semibold">User Settings</h2>

            <nav>
              <ul className="space-y-2">
                {navItems.map((item) => (
                  <li key={item}>
                    <button
                      type="button"
                      onClick={() => handleSidebarClick(item)}
                      className={`w-full text-left px-4 py-2 rounded transition-colors
                        ${activeTab === item ? "bg-gray-700" : "hover:bg-gray-700"}`}
                    >
                      {item}
                    </button>
                  </li>
                ))}
              </ul>
            </nav>
          </div>
        </aside>

        <main className="flex-1 px-6 py-8 bg-gray-50">
          <div className="max-w-4xl mx-auto">
            {renderContent()}
          </div>
        </main>
      </div>
    </div>
  );
}