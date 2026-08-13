import { useState } from 'react';
import Login from './Login';
import './App.css';

function App() {
  const [token, setToken] = useState<string | null>(null);
  const [message, setMessage] = useState('');

  const fetchSecuredData = async () => {
    try {
      const response = await fetch('http://localhost:8081/api/secured', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      if (response.ok) {
        const text = await response.text();
        setMessage(text);
      } else {
        setMessage(`Error: ${response.status}`);
      }
    } catch (err) {
      setMessage('Failed to connect to API');
    }
  };

  if (!token) {
    return <Login onLogin={setToken} />;
  }

  return (
    <div style={{ padding: '50px', textAlign: 'center' }}>
      <h2>Welcome! You are logged in.</h2>
      <p style={{ wordBreak: 'break-all', fontSize: '0.8em', color: 'gray' }}>Token: {token.substring(0, 50)}...</p>
      
      <button onClick={fetchSecuredData}>Fetch Secured Data from Spring Boot</button>
      
      {message && (
        <div style={{ marginTop: '20px', padding: '10px', border: '1px solid green', borderRadius: '4px' }}>
          <strong>API Response:</strong> {message}
        </div>
      )}
      
      <div style={{ marginTop: '50px' }}>
        <button onClick={() => setToken(null)} style={{ backgroundColor: 'red', color: 'white' }}>Logout</button>
      </div>
    </div>
  );
}

export default App;
