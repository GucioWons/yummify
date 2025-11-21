import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import AuthProvider from "./src/auth/context/AuthProvider.tsx";
import RestaurantProvider from "./src/restaurant/context/RestaurantProvider.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <AuthProvider>
          <RestaurantProvider>
              <App />
          </RestaurantProvider>
      </AuthProvider>
  </StrictMode>,
)
