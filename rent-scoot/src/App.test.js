import React from 'react'
import ReactDOM from 'react-dom'
import App from './components'

it('renders withoud crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App />, div)
})