import { Before, After } from '@cucumber/cucumber';
import { FullStackWorld } from './world';

Before(async function (this: FullStackWorld) {
  await this.init();
});

After(async function (this: FullStackWorld) {
  await this.cleanup();
});