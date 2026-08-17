export default {
  default: {
    requireModule: ['tsx'],
    paths: ['tests/features/**/*.feature'],
    require: ['tests/steps/**/*.ts', 'tests/support/**/*.ts'],
    format: ['progress', 'html:cucumber-report.html']
  }
}