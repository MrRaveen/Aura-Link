import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

// Get the root element from your HTML
const rootElement = document.getElementById('root');

// Create a root
const root = ReactDOM.createRoot(rootElement);

// Render your app component
root.render(
  <App />
);