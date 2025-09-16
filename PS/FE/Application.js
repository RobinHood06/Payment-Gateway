import React, { useState } from 'react';

export default function App() {
  const [recipientId, setRecipientId] = useState('');
  const [amount, setAmount] = useState('');
  const [message, setMessage] = useState('');

  const sendPayment = async () => {
    const response = await fetch('http://localhost:8081/payments/process', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        senderId: 1,
        recipientId: parseInt(recipientId),
        amount: parseFloat(amount),
        description: 'Test payment',
        status: 'PENDING'
      })
    });
    if (response.ok) setMessage("Payment successful!");
    else setMessage("Payment failed!");
  };

  return (
    <div style={{ padding: 20 }}>
      <h1>Send Money</h1>
      <input 
        placeholder="Recipient ID" 
        value={recipientId} 
        onChange={e => setRecipientId(e.target.value)} 
      />
      <br />
      <input 
        placeholder="Amount" 
        value={amount} 
        onChange={e => setAmount(e.target.value)} 
      />
      <br />
      <button onClick={sendPayment}>Send</button>
      <p>{message}</p>
    </div>
  );
}
