import { Given, When, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';
import { FullStackWorld } from '../support/world.ts'; // or just './world.ts' depending on your import

Given('the backend API is up and running', async function (this: FullStackWorld) {
  const response = await fetch('http://core-backend:8081/public/hello');
  if (!response.ok) throw new Error("Backend API is down!");
});

When('I visit the public hello page in the UI', async function (this: FullStackWorld) {
  await this.page!.goto('http://host.docker.internal:5173');
});

When('I log in with username {string} and password {string}', async function (this: FullStackWorld, user: string, pass: string) {
  // Fill in the Keycloak login form credentials
  await this.page!.fill('input[placeholder="Username"]', user);
  await this.page!.fill('input[placeholder="Password"]', pass);
  
  // Click the submit button
  await this.page!.click('button[type="submit"]');
});

When('I click the {string} button', async function (this: FullStackWorld, buttonText: string) {
  // Find the button by its exact text and click it
  const button = this.page!.locator(`button:has-text("${buttonText}")`);
  await button.click();
});

Then('I should see the message {string} on the screen', async function (this: FullStackWorld, expectedText: string) {
  // Wait for the exact text to render (like the "Welcome!" message)
  const element = this.page!.locator(`text="${expectedText}"`);
  await expect(element).toBeVisible({ timeout: 5000 });
});

Then('I should see the API response on the screen', async function (this: FullStackWorld) {
  // The App.tsx renders a <strong> tag that says "API Response:" when the backend returns data
  const element = this.page!.locator('strong:has-text("API Response:")');
  await expect(element).toBeVisible({ timeout: 5000 });
});