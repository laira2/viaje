// read-only
const router = require('express').Router();

const controller = require('./payments.controller');

router.route('/confirm').get(controller.confirmPayment);

module.exports = router;