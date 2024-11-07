import React from 'react';
import { render, screen } from '@testing-library/react';
import ChangeCalculator from '../app/page';
import '@testing-library/jest-dom'

describe('ChangeCalculator', () => {
  beforeEach(() => {
    jest.resetAllMocks();
  });

  it('renders the calculator form', () => {
    render(<ChangeCalculator />);
    
    screen.debug(); // Print the DOM to the console

    expect(screen.getByText('Change Calculator by Omoda')).toBeInTheDocument();
    expect(screen.getByText('Total Amount')).toBeInTheDocument();
    expect(screen.getByText('Amount Paid')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Calculate Change' })).toBeInTheDocument();
  });
});