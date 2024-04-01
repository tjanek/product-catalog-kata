import './App.css'
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ProductCatalog} from "@/features/product-catalog";

function App() {

  const queryClient = new QueryClient()

  return (
    <QueryClientProvider client={queryClient}>
        <ProductCatalog />
    </QueryClientProvider>
  )
}

export default App
