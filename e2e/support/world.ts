import { setWorldConstructor, World, IWorldOptions } from '@cucumber/cucumber';
import { Browser, BrowserContext, Page, chromium } from '@playwright/test';

export class FullStackWorld extends World {
  browser: Browser | null = null;
  context: BrowserContext | null = null;
  page: Page | null = null;
  apiResponse: string | null = null;

  constructor(options: IWorldOptions) {
    super(options);
  }

  async init() {
    this.browser = await chromium.launch({ headless: true });
    this.context = await this.browser.newContext();
    this.page = await this.context.newPage();
  }

  async cleanup() {
    await this.page?.close();
    await this.context?.close();
    await this.browser?.close();
  }
}

setWorldConstructor(FullStackWorld);