import { useEffect, useState } from 'react'
import './App.css'
import ProductList from './productList';
import CategoryFilter from './categoryFilter';

function App() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOrder, setSortOrder] = useState("asc");


  useEffect(() => {
    fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => setProducts(data));

    fetch('http://localhost:8080/api/categories')
    .then(response => response.json())
    .then(data => setCategories(data));
  }, []);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };
  const handleSortChange = (event) => {
    setSortTerm(event.target.value);
  };
  const handleCategorySelect = (categoryId) => {
    setSelectedCategory(categoryId ? Number(categoryId) : null);
  };

  console.log(selectedCategory);
  const filteredProducts = products.filter( product => {
    console.log(product)
    return (
      (selectedCategory ? product.category.id === selectedCategory : true) 
      &&
      product.name.toLowerCase().includes(searchTerm.toLowerCase())
    )
  })
  .sort((a,b) => {
    if(sortOrder === "asc"){
      return a.price - b.price;
    } else {
      return b.price - a.price;
    }
  });
  // console.log(filteredProducts);

  return (
    <div className='container'>
      <h1 className = 'my-4'> Product Catalog</h1>
      <div className='row align-items-center mb-4'>
        <div className = 'col-md-3 col-sm-12 mb-12'>
          <CategoryFilter categories={categories} onSelect={handleCategorySelect} />
        </div>
        <div className='col-md-5 col-sm-12 mb-2'>
          <input 
          type='text'
          className='form-control'
          placeholder='Search for products'
          onChange={handleSearchChange} />
        </div>
        <div className='col-md-4 col-sm-12 mb-2'>
          <select className='form-control' onChange={handleSortChange}>
          <option val="asc">Sort by price: Low to High</option>
          <option val="dsc">Sort by price: High to Low</option>
          </select>
        </div>
      </div>
      <div>
        {filteredProducts.length ? (
          <ProductList products={filteredProducts}/>
        ) : (
          <p>No products are found</p>
        )}
      </div>

    </div>
    
  )
}

export default App
