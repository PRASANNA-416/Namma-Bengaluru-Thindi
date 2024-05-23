import Navbar from './components/Navbar/Navbar';
import './App.css';
import { CssBaseline, ThemeProvider } from '@mui/material';
import { darktheme } from './Theme/DarkTheme';
import Home from './components/Home/Home';
import RestaurantDetails from './components/Restaurant/RestaurantDetails';
import Cart from 'components/Cart/Cart';
import Profile from 'components/Profile/Profile';


function App() {
  return (
    <ThemeProvider theme={darktheme}>
    
      <CssBaseline/>
      <Navbar/>
      {/*<Home/>*/}
      {/*<RestaurantDetails/>*/}
      {/*<Cart/>*/}
      <Profile/>

    </ThemeProvider>

  );
}

export default App;
