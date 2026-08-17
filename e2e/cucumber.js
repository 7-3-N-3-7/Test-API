export default {
  default: {
    requireModule: ['tsx'],
    paths: ['features/**/*.feature'],
    import: ['steps/**/*.ts', 'support/**/*.ts'],
    format: ['progress']
  }
}