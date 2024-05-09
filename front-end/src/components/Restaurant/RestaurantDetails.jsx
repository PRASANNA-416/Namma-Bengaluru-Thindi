import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from '@mui/material'
import React, { useState } from 'react';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

const categories = [
    "pizza",
    "Biryani",
    "Burger",
    "chicken",
    "rice"
]

const foodTypes =[
    {
        label:"All",
        value:"all"
    },
    {
        value:"Vegetarian only",
        label:"vegetarian"
    },
    {
        value:"Non-Vegetarian",
        label:"non_vegetarian"
    },
    {
        value:"Seasonal",
        label:"seasonal"
    },
]
const RestaurantDetails = () => {

    const [foodType, setFoodType] = useState("all"); // Correct variable name

    const handleFilter = (e) => {
        setFoodType(e.target.value, e.target.name)
    }
  return (
    <div className='px-5 lg:px-20'>
        <section>
            <h3 className="text-gray-500 py-2 mt-10">Home/India/Indian fast food/3</h3>
            <div>
                <Grid container spacing={2}>
                    <Grid item xs={12}>

                    <img className="w-full h-[40vh] object cover" src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg" alt =""/>
                    </Grid>
                    <Grid item xs={12} lg={6}>

                    <img className="w-full h-[40vh] object cover" src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg" alt =""/>
                    </Grid>
                    <Grid item xs={12} lg={6}>

                    <img className="w-full h-[40vh] object cover" src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg" alt =""/>
                    </Grid>
                </Grid>
            </div>

            <div className='pt-3 pb-5'>
                <h1  className='text-4xl font-semibold'>Indian Fast Food</h1>

                <p className='text-gray-500 mt-1'>This is the restaurant description serving all the fast foods</p>
                <div className='space-y-3 mt-3'>
                    <p className='text-gray-500 flex items=center gap-3'>
                    <LocationOnIcon/>
                        <span>Mumbai, Maharashtra</span>
                    </p>

                    <p className='text-gray-500 flex items=center gap-3'>
                    <CalendarTodayIcon/>
                        <span>Mon-Sun: 9:00AM - 9:00PM</span>
                    </p>

                </div>

            </div>
        </section>

        <Divider/>

        <section className='pt-[2rem] lg:flex relative'>
            <div className='space-y-10 lg:w-[20%] filter'>

                {/* there was lg:sticky before top-28 in both the div tags */}
                <div className='box space-y-5 top-28  p-5 '>
                    <div>
                        <Typography variant='h5' sx={{paddingBottom:'1rem'}}>
                            Food Type
                        </Typography>

                         <FormControl className='py-10 space-y-5' component={"fieldset"}>
                            <RadioGroup onChange={handleFilter} name="food_Type" value={foodType}>
                            {foodTypes.map((item) => (
                                <FormControlLabel 
                                    key = {item.value}
                                    value={item.value} 
                                    control={<Radio />} 
                                    label={item.label}/>
                                
                                ))}
                            </RadioGroup>
                         </FormControl>
                    </div>
                </div>

                <Divider/>

                <div className='box space-y-5 top-28'>
                <div>
                    <Typography variant='h5' sx={{paddingBottom:'1rem'}}>
                        Food Catogory
                    </Typography>

                     <FormControl className='py-10 space-y-5' component={"fieldset"}>
                        <RadioGroup onChange={handleFilter} name="food_Type" value={foodType}>
                        {categories.map((item) => (
                            <FormControlLabel 
                                key = {item}
                                value={item} 
                                control={<Radio />} 
                                label={item}/>
                            
                            ))}
                        </RadioGroup>
                     </FormControl>
                </div>
            </div>
                
            </div>

            <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                menu
            </div>

        </section>
      
    </div>
  )
}

export default RestaurantDetails
