import { Given, When, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';
import { FullStackWorld } from '../support/world.ts';

Given('the backend API is up and running', async function (this: FullStackWorld) {
  // Hit the Java Spring Boot API container directly!
  const response = await fetch('http://core-backend:8081/public/hello');
  if (!response.ok) throw new Error("Backend API is down!");
  
  this.apiResponse = await response.text(); 
});

When('I visit the public hello page in the UI', async function (this: FullStackWorld) {
  // Hit the Vite Frontend. "host.docker.internal" is used because your 
  // Vite UI server (npm run dev) is running on your Windows host machine, not in Docker!
  await this.page!.goto('http://host.docker.internal:5173');
});

Then('I should see the message {string} on the screen', async function (this: FullStackWorld, expectedText: string) {
  // Wait for the UI to display the exact text
  const element = this.page!.locator(`text="${expectedText}"`);
  await expect(element).toBeVisible();
});