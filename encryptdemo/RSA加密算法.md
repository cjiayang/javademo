# RSA加密算法

文章引用自：https://wujingchao.github.io/2016/04/03/java-rsa-encryption/

### Java使用模数、公钥指数、私钥指数进行RSA加解密

关于模数n、公钥指数e、私钥指数d的相关概念推荐阮老师的这两篇文章。

[RSA算法原理（一）](http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html)
[RSA算法原理（二）](http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html)

简单说一下，模数n就是随机选取的两个质数p,q的乘积，并且n的长度就是秘钥的长度。根据欧拉函数，得到

```
 φ(n) = (p-1)(q-1)。
```

公钥钥指数e随机选取，与φ(n)互质，通常为65537。然后根据　

```
ed ≡ 1 (mod φ(n)) //等价于ed - 1 = kφ(n)
```

计算e对于φ(n)的反模元素d，即得到私钥指数d。

(n,e)就作为公钥，对数据进行加密，(n,d)就作为私钥，对数据进行解密。

根据公钥(n,e)来推导私钥(n,d)的步骤:
对这个方程求解 ed - 1 = kφ(n)，这个方程已知的参数为e，想要计算出d，就需要知道φ(n)。
想要知道φ(n)就需要知道p和q。想要知道p和q就需要对n进行因数分解，所以 **对大整数n的因数分解难度决定了RSA算法的可靠性** 。

> 给出两个大数很容易将他们相乘，但是给出他们的乘积找出因子就不是那么容易了。 – Wikipedia

先来看一下怎么生成模数、公钥指数和私钥指数:

```java
KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
generator.initialize(1024);
KeyPair pair = generator.generateKeyPair();
RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
println("publicKey exponent:" + publicKey.getPublicExponent());
println("publicKey modules:" + publicKey.getModulus());
println("publicKey format:" + publicKey.getFormat());
println("---------------------华丽的分割线-------------------------");
println("privateKey exponent:" + privateKey.getPrivateExponent());
println("privateKey modules:" +  privateKey.getModulus());
println("privateKey format:" + privateKey.getFormat());
```

打印结果:

publicKey exponent:65537
publicKey modules:154471992999058139479994460025815654498183391593444870454838266974581244599191659985455957889064163942388409487313472074598227824609910604156744751985833898809065078785899074110993629452358669379496163284362583792866500058660069050752020922895749548342185553141417346777273482310707415185758164008066298773949
publicKey format:X.509
---------------------华丽的分割线-------------------------
privateKey exponent:24953766420205815381764520016071994967304996670579990593182061010725111564027070269710579156377653900210050677360692873548856950717077735724971492275722465522175892883197573916804276397143284954594245180776141869860033925480138858143033802945465036705957639063440190950861284456594945244826689811470380537909
privateKey modules:154471992999058139479994460025815654498183391593444870454838266974581244599191659985455957889064163942388409487313472074598227824609910604156744751985833898809065078785899074110993629452358669379496163284362583792866500058660069050752020922895749548342185553141417346777273482310707415185758164008066298773949
privateKey format:PKCS#8

然后就可以保存结果，下面是加解密的过程：

```java
//公钥加密
KeyFactory keyFactory = KeyFactory.getInstance("RSA");
BigInteger modulus = new BigInteger("154471992999058139479994460025815654498183391593444870454838266974581244599191659985455957889064163942388409487313472074598227824609910604156744751985833898809065078785899074110993629452358669379496163284362583792866500058660069050752020922895749548342185553141417346777273482310707415185758164008066298773949",10);
BigInteger publicExponent = new BigInteger("65537");
RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus,publicExponent);
PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE,publicKey);
byte[]  encryptData = cipher.doFinal("Hello,RSA!".getBytes());
//私钥解密
BigInteger privateExponent = new BigInteger("24953766420205815381764520016071994967304996670579990593182061010725111564027070269710579156377653900210050677360692873548856950717077735724971492275722465522175892883197573916804276397143284954594245180776141869860033925480138858143033802945465036705957639063440190950861284456594945244826689811470380537909",10);
RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus,privateExponent);
PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
cipher.init(Cipher.DECRYPT_MODE,privateKey);
byte[] decryptData = cipher.doFinal(encryptData);
println("decryptData:" + new String(decryptData));
```

打印结果:

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><p><code><span class="pln">decryptData</span><span class="pun">:</span><span class="typ">Hello</span><span class="pun">,</span><span class="pln">RSA</span><span class="pun">!</span></code></p></li></ol></pre>

题外：另外需要注意的一点是Cipher这个实例是有状态的，所以不是线程安全的。[is-cipher-thread-safe?](http://stackoverflow.com/questions/6957406/is-cipher-thread-safe)
