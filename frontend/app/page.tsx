'use client';

import { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from "@/components/ui/label"
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"

export default function ChangeCalculator() {
  const [valuta, setValuta] = useState('euro');
  const [totalAmount, setTotalAmount] = useState('');
  const [amountPaid, setAmountPaid] = useState('');

  const [result, setResult] = useState<{
    totalChange: number;
    denominations: Record<string, number>;
    status: number;
    text: string;
  } | null>(null);
  const [error, setError] = useState('');
  const [backResponse] = useState("Nothing");

  const calculateChange = async () => {
    try {
      setError('');
      const response = await fetch('http://127.0.0.1:8080/api/calculate-change', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          valuta: valuta,
          totalAmount: parseFloat(totalAmount),
          amountPaid: parseFloat(amountPaid),
        }),
        credentials: 'include', 
      });

      if (!response.ok) {
        throw new Error('Error calculating change. Please check your inputs.');
      }

      const data = await response.json();
      setResult(data);

    } catch (err) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError('An unknown error occurred.');
      }
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <Card className="max-w-md mx-auto">
        <CardHeader>
          <CardTitle>Change Calculator by Omoda</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
          <div>
              <label className="block text-sm font-medium mb-1">
                Valuta
              </label>
              <RadioGroup defaultValue="euro"
                       onValueChange={(value) => setValuta(value)}
              >
                <div className="flex items-center space-x-2">
                  <RadioGroupItem value="euro" id="euro" 
   
                  />
                  <Label htmlFor="euro">Euro</Label>
                </div>
                <div className="flex items-center space-x-2">
                  <RadioGroupItem value="dollar" id="dollar" 
   
                  />
                  <Label htmlFor="dollar">Dollar</Label>
                </div>
              </RadioGroup>
            </div>
            <div>
              <label className="block text-sm font-medium mb-1">
                Total Amount
              </label>
              <Input
                type="number"
                step="0.01"
                value={totalAmount}
                onChange={(e) => setTotalAmount(e.target.value)}
                placeholder="Enter total amount"
              />
            </div>
            
            <div>
              <label className="block text-sm font-medium mb-1">
                Amount Paid
              </label>
              <Input
                type="number"
                step="0.01"
                value={amountPaid}
                onChange={(e) => setAmountPaid(e.target.value)}
                placeholder="Enter amount paid"
              />
            </div>

            <Button 
              onClick={calculateChange}
              className="w-full"
              disabled={!totalAmount || !amountPaid}
            >
              Calculate Change
            </Button>

            {error && (
              <div className="text-red-500 text-sm">{error}</div>
            )}

            {result && (
              <div className="mt-4">
                <h3 className="font-medium text-lg mb-2">
                  Total Change: ${result.totalChange.toFixed(2)}
                </h3>
                <div className="space-y-2">
                  {Object.entries(result.denominations).map(([denom, count]) => (
                    <div key={denom} className="flex justify-between">
                      <span>{denom}:</span>
                      <span>{count}</span>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        </CardContent>
      </Card>
    </div>
  );
}