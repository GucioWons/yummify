import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import AuthProvider from "./src/auth/context/AuthProvider.tsx";
import RestaurantProvider from "./src/restaurant/context/RestaurantProvider.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClient = new QueryClient()

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <AuthProvider>
          <QueryClientProvider client={queryClient}>
              <RestaurantProvider>
                  <App />
              </RestaurantProvider>
          </QueryClientProvider>
      </AuthProvider>
  </StrictMode>,
)
