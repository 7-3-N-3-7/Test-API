import { Given, When, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';
import { FullStackWorld } from '../support/world';

Given('the backend API is up and running', async function (this: FullStackWorld) {
  // Directly hit the Spring Boot API (Port 8081)
  const response = await fetch('http://localhost:8081/public/hello');
  if (!response.ok) throw new Error("Backend API is down!");
  
  // Save the response in the World context to use in later steps if needed
  this.apiResponse = await response.text(); 
});

When('I visit the public hello page in the UI', async function (this: FullStackWorld) {
  // Hit the Vite Frontend (Port 5173)
  await this.page!.goto('http://localhost:5173');
});

Then('I should see the message {string} on the screen', async function (this: FullStackWorld, expectedText: string) {
  // Wait for the UI to display the exact text that came from the backend
  const element = this.page!.locator(`text="${expectedText}"`);
  await expect(element).toBeVisible();
});