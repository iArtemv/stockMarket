### Stock Market Simulator

Allows a client to trade on the market. Exposes these functions:

- Add order
- Cancel order

```cli
> add GOOG B 100 50
[2020-05-01T12:34:56+00:00] Order with ID 1 added: GOOG Buy 100 @ 50
> add GOOG S 30 50
[2020-05-01T12:34:58+00:00] Order with ID 2 added: GOOG Sell 30 @ 50
[2020-05-01T12:34:59+00:00] New execution with ID 1: GOOG 30 @ 50 (orders 1 and 2)
