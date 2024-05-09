import Navbar from './components/Navbar/Navbar';
import './App.css';
import { CssBaseline, ThemeProvider } from '@mui/material';
import { darktheme } from './Theme/DarkTheme';
import Home from './components/Home/Home';
import RestaurantDetails from './components/Restaurant/RestaurantDetails';


function App() {
  return (
    <ThemeProvider theme={darktheme}>
    
      <CssBaseline/>
      <Navbar/>
      {/*<Home/>*/}
      <RestaurantDetails/>

    </ThemeProvider>

  );
}

export default App;
