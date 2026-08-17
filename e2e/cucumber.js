export default {
  default: {
    requireModule: ['tsx'],
    paths: ['features/**/*.feature'],
    require: ['steps/**/*.ts', 'support/**/*.ts'],
    format: ['progress']
  }
}