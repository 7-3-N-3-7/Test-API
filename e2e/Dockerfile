# Official Playwright image includes Node.js and browser binaries
FROM mcr.microsoft.com/playwright:v1.62.1-jammy

WORKDIR /e2e

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy test files
COPY . .

# When the container starts, wait a moment for the backend to be fully ready, then run the tests
CMD sleep 15 && npm test