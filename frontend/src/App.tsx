import './App.css'
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ProductCatalog} from "@/features/product-catalog";
import {SnackbarProvider} from "notistack";

function App() {

  const queryClient = new QueryClient()

  return (
    <QueryClientProvider client={queryClient}>
        <SnackbarProvider maxSnack={3}>
            <ProductCatalog />
        </SnackbarProvider>
    </QueryClientProvider>
  )
}

export default App
