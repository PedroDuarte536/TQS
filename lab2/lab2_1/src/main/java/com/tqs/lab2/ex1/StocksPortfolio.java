package com.tqs.lab2.ex1;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

  private List<Stock> stocks = new ArrayList<>();
  private IStockmarketService stockmarket;

  public StocksPortfolio (IStockmarketService stockmarket) {
    this.stockmarket = stockmarket;
  }

  public void addStock (Stock stock) {
    stocks.add(stock);
  }

  public double getTotalValue () {
    return stocks.stream()
      .map((stock) -> stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel()))
      .reduce(0.0, (sum, value) -> sum + value);
  }
  
}
