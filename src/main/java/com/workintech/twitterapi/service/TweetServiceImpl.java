package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet save(Tweet tweet) {
        // Gelen tweet nesnesini veritabanına kaydet
        // Not: createdAt ve updatedAt, Entity içindeki anotasyonlarla otomatik yönetiliyor.
        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getAll() {
        // Veritabanındaki tüm tweetleri bir liste olarak getir.
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getById(Long id) {
        // Verilen IDye göre bir tweet ara
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        // Eğer tweet bulunduysa onu döndür
        if(optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        // Bulunamazsa hata fırlat
        throw new RuntimeException("Tweet not found with id: " + id);

        // TODO: Burası için ---TweetNotFoundException--- gibi özel bir hata sınıfı oluşturucam
    }

    @Override
    public List<Tweet> getByUserId(Long userId) {
        // Repositorydeki özel sorguyu kullan ve kullanıcı IDsine ait tüm tweetleri getir
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet update(Long id, Tweet tweet) {
        // Önce güncellenecek olan tweeti IDsi ile veritabanından bul
        Tweet existingTweet = getById(id);
        // Bulduğun tweetin içeriğini dışarıdan gelen yeni tweetin içeriği ile değiştir
        existingTweet.setContent(tweet.getContent());
        // Değiştirilmiş tweeti veritabanına kaydet
        // Not: updatedAt, Entity içindeki @PreUpdate ile otomatik güncellenecek.
        return tweetRepository.save(existingTweet);
    }

    @Override
    public void delete(Long id) {
        // Verilen IDye sahip tweeti veritabanından sil.
        tweetRepository.deleteById(id);
        // TODO: Buraya, Spring Security eklendikten sonra
        //  silme işlemini sadece tweet'in sahibinin yapabildiğinden emin olan
        //  bir güvenlik kontrolü (iş kuralı) ekleyeceğğm.
    }
}