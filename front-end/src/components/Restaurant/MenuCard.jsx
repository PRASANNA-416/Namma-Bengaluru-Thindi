import React from 'react';
import { Accordion, AccordionDetails, AccordionSummary, Button, Checkbox, FormControlLabel, FormGroup } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

// Example data with unique identifiers
const demo = [
  {
    category: 'Nuts & Seeds',
    ingredient: ['Cashews'],
  },
  {
    category: 'Protein',
    ingredient: ['Chicken', 'Beef'],
  },
];

const MenuCard = () => {

  const handleCheckBoxChange=(value)=>{
      console.log("value")
  }
  return (
    <Accordion>
      <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1-content" id="panel1-header">
        <div className="lg:flex items-center justify-between">
          <div className="lg:flex items-center lg:gap-5">
            <img
              className="w-[7rem] h-[7rem] object-cover"
              src="https://www.shutterstock.com/image-photo/classic-hamburger-stock-photo-isolated-600nw-2282033179.jpg"
              alt=""
            />
            <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
              <p className="text-xl font-semibold">Burger</p>
              <p>₹ 200</p>
              <p className="text-gray-400">Burger made of maida and chicken burger</p>
            </div>
          </div>
        </div>
      </AccordionSummary>

      <AccordionDetails>
        <form>
          <div className="flex gap-5 flex-wrap">
            {demo.map((item, idx) => (
              <div key={idx}>
                <p>{item.category}</p>
                {item.ingredient && (
                  <FormGroup>
                    {item.ingredient.map((ingredient, innerIdx) => (
                      <FormControlLabel key={innerIdx} control={<Checkbox onChange={() => handleCheckBoxChange(item)}/>} label={ingredient} />
                    ))}
                  </FormGroup>
                )}
              </div>
            ))}
          </div>
          
          <div className="pt-5">
            <Button variant="contained" disabled={false} type="submit">{true?"Add to Cart":"Out of Stock"}</Button>
          </div>
        </form>
      </AccordionDetails>
    </Accordion>
  );
};

export default MenuCard;
