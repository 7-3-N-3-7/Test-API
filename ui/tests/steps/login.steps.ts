import { Given, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';
import { CustomWorld } from '../support/world';

Given('I visit the homepage', async function (this: CustomWorld) {
  // Make sure your Vite dev server (npm run dev) is running on port 5173!
  await this.page!.goto('http://localhost:5173');
});

Then('I should see the {string} heading', async function (this: CustomWorld, headingText: string) {
  const heading = this.page!.locator('h2', { hasText: headingText });
  await expect(heading).toBeVisible();
});