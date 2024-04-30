import Navbar from './components/Navbar/Navbar';
import './App.css';
import { CssBaseline, ThemeProvider } from '@mui/material';
import { darktheme } from './Theme/DarkTheme';
import Home from './components/Home/Home';


function App() {
  return (
    <ThemeProvider theme={darktheme}>
    
      <CssBaseline/>
      <Navbar/>
      <Home/>

    </ThemeProvider>

  );
}

export default App;
