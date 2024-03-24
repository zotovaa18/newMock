# Задание

Доработать заглушку/эмулятор который мы написали на занятии.
Добавить логику:
Если номер клиента начинается с 8 то валюта счета(currency) доллар - US
Если номер клиента начинается с 9 то валюта счета(currency) евро - EU
Если начинается с любой другой цифру то валюта счета(currency) рубль - RUB

Так же сделать рандомный баланс меньше максимального лимита.

## Решение
Для реализации текущего задания было доработано следующее условие:
```
if (firstDigit == '8') {
    maxLimit = new BigDecimal(2000);
    currency = "US";
} else if (firstDigit == '9') {
    maxLimit = new BigDecimal(1000);
    currency = "EU";
} else {
    maxLimit = new BigDecimal(10000);
    currency = "RUB";
}
```

Для вывода баланса были написаны следующие строки кода (реализовано округление до целых):
```
BigDecimal balance = new BigDecimal(new Random().nextDouble()).multiply(maxLimit);
BigDecimal balanceRounded = balance.setScale(0, RoundingMode.HALF_UP);
```

