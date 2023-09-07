package com.aifunny.profile

import com.aifunny.model.profile.CreateProfileRequest
import com.aifunny.model.profile.ProfileResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.lang.NullPointerException

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class ProfileController {

    @Autowired
    lateinit var profileService: ProfileService

    @RequestMapping("/profile", method = [RequestMethod.POST])
    fun createProfile(@RequestBody request: CreateProfileRequest): ProfileResponse {
        return profileService.createProfile(request)
    }

    @RequestMapping("/profile/{profileId}", method = [RequestMethod.GET])
    fun getProfile(@PathVariable("profileId") profileId: String): ProfileResponse {
        return profileService.getProfile(profileId) ?: run {
            throw NullPointerException("Profile Not Found.")
        }
    }
}